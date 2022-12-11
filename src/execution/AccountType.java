package execution;

public enum AccountType {
    STANDARD {
        @Override
        public final String toString() {
            return "standard";
        }
    },
    PREMIUM {
        @Override
        public final String toString() {
            return "premium";
        }
    };

    public String toString() {
        return "null AccountType";
    }
}
