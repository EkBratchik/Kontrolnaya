package com.bsu;

public class FileException extends Exception {
        public FileException(String text) {
            super(text);
        }
        public FileException(String text, Throwable reason) {
            super(text, reason);
        }
    }

