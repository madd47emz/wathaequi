package com.example.auth.util;

import java.io.Serializable;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class AlgerianNINGenerator implements IdentifierGenerator {
        private static final int NIN_LENGTH = 10;

        @Override
        public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
            // Generate a random 9-digit NIN
            String nin = generateRandomNIN(NIN_LENGTH - 1);

            // Calculate the check digit for the NIN
            int checkDigit = calculateCheckDigit(nin);
            nin += checkDigit;

            return nin;
        }

        private String generateRandomNIN(int length) {
            // Generate a random NIN by concatenating random digits
            StringBuilder sb = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                sb.append((int) (Math.random() * 10));
            }
            return sb.toString();
        }

        private int calculateCheckDigit(String nin) {
            // Calculate the check digit for the NIN
            int sum = 0;
            for (int i = 0; i < nin.length(); i++) {
                int digit = Character.getNumericValue(nin.charAt(i));
                if (i % 2 == 0) {
                    digit *= 2;
                    if (digit > 9) {
                        digit -= 9;
                    }
                }
                sum += digit;
            }
            int checkDigit = 10 - (sum % 10);
            if (checkDigit == 10) {
                checkDigit = 0;
            }
            return checkDigit;
        }
    }
