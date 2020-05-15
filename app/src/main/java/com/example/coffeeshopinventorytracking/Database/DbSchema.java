package com.example.coffeeshopinventorytracking.Database;

public class DbSchema {

    public static final class SyrupTable{
        public static final String NAME = "syrups";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String NAME = "name";
            public static final String SUGAR = "sugar";
            public static final String STOCK = "stock";
            public static final String MINIMUM = "minimum";
        }
    }
    public static final class CupTable {
        public static final String NAME = "cups";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String SIZE = "size";
            public static final String HOTCOLD = "hotcold";
            public static final String QUANTITY = "quantity";
            public static final String MINIMUM = "minimum";
            public static final String LID_FOREIGN_ID = "lid_id";
        }
    }

    public static final class LidTable{
        public static final String NAME = "lids";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String SIZE = "size";
            public static final String HOTCOLD = "hotcold";
            public static final String QUANTITY = "quantity";
            public static final String MINIMUM = "minimum";
            public static final String CUP_FOREIGN_ID = "cup_id";
        }
    }

    public static final class PastryTable{
        public static final String NAME = "pastries";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String NAME = "name";
            public static final String QUANTITY = "quantity";
            public static final String MINIMUM = "minimum";
        }
    }

    public static final class CreamTable{
        public static final String NAME = "creams";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String NAME = "name";
            public static final String DAIRY = "dairy";
            public static final String QUANTITY = "quantity";
            public static final String MINIMUM = "minimum";
        }
    }

    public static final class CoffeeTable{
        public static final String NAME = "coffees";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String GROUNDWHOLE = "groundwhole";
            public static final String ROAST = "roast";
            public static final String QUANTITY = "quantity";
            public static final String MINIMUM = "minimum";
        }
    }

    public static final class UserTable{
        public static final String NAME = "user";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String USERNAME = "username";
            public static final String PASSWORD = "password";
        }
    }
}
