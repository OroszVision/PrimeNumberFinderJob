    package org.example;

    import org.apache.poi.ss.usermodel.*;
    import org.apache.poi.xssf.usermodel.XSSFWorkbook;
    import org.apache.logging.log4j.LogManager;
    import org.apache.logging.log4j.Logger;
    import org.psjava.algo.math.numbertheory.PrimeNumberSieve;
    import org.psjava.algo.math.numbertheory.SieveOfEratosthenes;
    import org.psjava.ds.array.Array;

    import java.io.FileInputStream;
    import java.io.IOException;
    import java.util.HashSet;
    import java.util.Set;

    public class PrimeNumberFinder {
        private static final Logger logger = LogManager.getLogger(PrimeNumberFinder.class);

        public static void main(String[] args) {
            if (args.length != 1) {
                logger.error("Please provide the path to the Excel file as the only argument.");
                return;
            }
            String filePath = args[0];

            int maxNumber = 1000000;

            PrimeNumberSieve sieve = SieveOfEratosthenes.getInstance();
            Array<Integer> primeArray = sieve.calcList(maxNumber);
            Set<Integer> primeSet = new HashSet<>();
            for (int i = 0; i < primeArray.size(); i++) {
                primeSet.add(primeArray.get(i));
            }

            try (FileInputStream fis = new FileInputStream(filePath);
                 Workbook workbook = new XSSFWorkbook(fis)) {

                Sheet sheet = workbook.getSheetAt(0);
                for (Row row : sheet) {
                    Cell cell = row.getCell(1);
                    if (cell != null) {
                        try {
                            double numericValue = cell.getNumericCellValue();
                            if (numericValue % 1 == 0 && numericValue >= 0) {
                                int value = (int) numericValue;
                                if (primeSet.contains(value)) {
                                    logger.info("{}", value);
                                }
                            }
                        } catch (IllegalStateException e) {
                            String cellValue = cell.toString().trim();
                            try {
                                double numericValue = Double.parseDouble(cellValue);
                                if (numericValue % 1 == 0 && numericValue >= 0) {
                                    int value = (int) numericValue;
                                    if (primeSet.contains(value)) {
                                        logger.info("{}", value);
                                    }
                                }
                            } catch (NumberFormatException ex) {
                                logger.warn("Invalid data encountered: {}", cellValue);
                            }
                        }
                    } else {
                        logger.warn("Invalid data encountered: null");
                    }
                }

            } catch (IOException e) {
                logger.error("Error reading the Excel file or file doesn't exist!", e);
            }
        }
    }
