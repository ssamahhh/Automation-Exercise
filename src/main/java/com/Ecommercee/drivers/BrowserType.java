package com.Ecommercee.drivers;

public enum BrowserType {

    CHROME {
        @Override
        public AbstractDriver getDriverFactory() {
            return new chromeFactory();
        }
    },

    FIREFOX {
        @Override
        public AbstractDriver getDriverFactory() {
            return new FirefoxFactory();
        }
    },

    EDGE {
        @Override
        public AbstractDriver getDriverFactory() {
            return new EdgeFactory();
        }
    },

    SAFARI {
        @Override
        public AbstractDriver getDriverFactory() {
            return new SafariFactory();
        }
    };

    public abstract AbstractDriver getDriverFactory();
}
