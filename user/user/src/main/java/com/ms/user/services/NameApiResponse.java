package com.ms.user.services;

public class NameApiResponse {

    private String gender;
    private Usage[] usages; // Representa os usos do nome

    // Getters e Setters

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Usage[] getUsages() {
        return usages;
    }

    public void setUsages(Usage[] usages) {
        this.usages = usages;
    }

    public static class Usage {
        private String usage_code;
        private String usage_full;
        private String usage_gender;

        // Getters e Setters

        public String getUsage_code() {
            return usage_code;
        }

        public void setUsage_code(String usage_code) {
            this.usage_code = usage_code;
        }

        public String getUsage_full() {
            return usage_full;
        }

        public void setUsage_full(String usage_full) {
            this.usage_full = usage_full;
        }

        public String getUsage_gender() {
            return usage_gender;
        }

        public void setUsage_gender(String usage_gender) {
            this.usage_gender = usage_gender;
        }
    }
}