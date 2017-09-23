package bid.ApixTeam.bot.utils.vars.enums;

/**
 * TSC-Bot was created by ApixTeam (C) 2017
 * in association with TheSourceCode (C) 2017
 */
public enum SimpleRank {
    JR_MOD("Junior Moderator"),
    SR_MOD("Senior Moderator"),
    JR_ADMIN("Junior Administrator"),
    SR_ADMIN("Senior Administrator");

    final String description;

    SimpleRank(String desc) {
        this.description = desc;
    }

    public static SimpleRank getRank(String search) {
        for (SimpleRank rank : values())
            if (rank.description.equalsIgnoreCase(search)) return rank;
        return null;
    }

    public boolean isAtLeast(SimpleRank permissions) {
        return this.ordinal() >= permissions.ordinal();
    }

    public boolean isHigherThan(SimpleRank permissions) {
        return this.ordinal() > permissions.ordinal();
    }

    public boolean isLowerThan(SimpleRank permissions) {
        return this.ordinal() < permissions.ordinal();
    }

    public String getDescription() {
        return description;
    }
}
