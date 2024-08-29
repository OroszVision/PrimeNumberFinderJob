package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;



import static org.junit.jupiter.api.Assertions.assertTrue;

public class PrimeNumberFinderTest {
    private static final String TEST_EXCEL_FILE = "src/main/resources/vzorek_dat.xlsx";
    private static MockAppender mockAppender;

    @BeforeAll
    static void setup() {
        // Initialize and add the mock appender
        mockAppender = new MockAppender();
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        context.getConfiguration().addAppender(mockAppender);
        context.getRootLogger().addAppender(mockAppender);
        mockAppender.start();
    }

    @AfterAll
    static void teardown() {
        // Clean up and remove the mock appender
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        context.getConfiguration().getAppenders().remove("MockAppender");
    }

    @Test
    public void testValidPrimeNumberInCell() {
        // Simulate args
        String[] args = {TEST_EXCEL_FILE};

        // Call the method
        PrimeNumberFinder.main(args);

        // Verify logging for the specific prime number
        assertTrue(mockAppender.getMessages().contains("15619"), "Expected log message not found");
        assertTrue(mockAppender.getMessages().contains("211"), "Expected log message not found");
        assertTrue(mockAppender.getMessages().contains("7"), "Expected log message not found");
        assertTrue(mockAppender.getMessages().contains("23311"), "Expected log message not found");
        assertTrue(mockAppender.getMessages().contains("54881"), "Expected log message not found");
    }


    @Test
    public void testInvalidDataHandling() {
        String[] args = {TEST_EXCEL_FILE};
        PrimeNumberFinder.main(args);


        String logMessages = mockAppender.getMessages();


        assertTrue(logMessages.contains("Invalid data encountered: Data"), "Expected log message not found for 'Data'");
        assertTrue(logMessages.contains("Invalid data encountered: x"), "Expected log message not found for 'x'");
    }


    @Test
    public void testNonNumericCellValue() {
        String[] args = {TEST_EXCEL_FILE};

        PrimeNumberFinder.main(args);

        assertTrue(mockAppender.getMessages().contains("Invalid data encountered: Data"), "Expected log message not found");
        assertTrue(mockAppender.getMessages().contains("Invalid data encountered: x"), "Expected log message not found");

    }

    @Test
    public void testNoFileFound() {
        String[] args = {"dummy.xlsx"};

        PrimeNumberFinder.main(args);

        assertTrue(mockAppender.getMessages().contains("Error reading the Excel file or file doesn't exist!"), "Expected log message not found");
    }

    private static class MockAppender extends AbstractAppender {
        private final StringWriter writer = new StringWriter();

        protected MockAppender() {
            super("MockAppender", null, null, true, null);
        }

        @Override
        public void append(LogEvent event) {
            writer.write(event.getMessage().getFormattedMessage() + "\n");
        }

        public String getMessages() {
            return writer.toString();
        }
    }

}
