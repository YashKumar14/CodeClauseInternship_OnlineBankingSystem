package com.example.OnlineBankingSystem;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.example.OnlineBankingSystem.Entity.Account;
import com.example.OnlineBankingSystem.Entity.Transaction;
import com.fasterxml.jackson.core.type.TypeReference;

public class RestClient {

    private static final String BASE_URL = "http://localhost:8080/accounts";
    private static final ObjectMapper mapper = new ObjectMapper();
    
    static {
        // Register the module for Java 8 date/time types
        mapper.registerModule(new JavaTimeModule());
    }

    public static Account addAccount(Account account) throws Exception {
        URL url = new URL(BASE_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        String requestBody = mapper.writeValueAsString(account);
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = requestBody.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
        StringBuilder response = new StringBuilder();
        String responseLine;
        while ((responseLine = br.readLine()) != null) {
            response.append(responseLine.trim());
        }

        return mapper.readValue(response.toString(), Account.class);
    }

    public static void transferFunds(String fromAccount, String toAccount, double amount) throws Exception {
        String urlString = String.format("%s/transfer?fromAccount=%s&toAccount=%s&amount=%f", BASE_URL, fromAccount, toAccount, amount);
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");

        connection.getResponseCode();
    }

    public static Account getAccountByNumber(String accountNumber) throws Exception {
        URL url = new URL(BASE_URL + "/" + accountNumber);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
        StringBuilder response = new StringBuilder();
        String responseLine;
        while ((responseLine = br.readLine()) != null) {
            response.append(responseLine.trim());
        }

        return mapper.readValue(response.toString(), Account.class);
    }

    public static List<Account> getAllAccounts() throws Exception {
        URL url = new URL(BASE_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
        StringBuilder response = new StringBuilder();
        String responseLine;
        while ((responseLine = br.readLine()) != null) {
            response.append(responseLine.trim());
        }

        return mapper.readValue(response.toString(), new TypeReference<List<Account>>() {});
    }
    
    public static List<Transaction> getTransactionsByAccount(String accountNumber) throws Exception {
        URL url = new URL(BASE_URL + "/" + accountNumber + "/transactions");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
        StringBuilder response = new StringBuilder();
        String responseLine;
        while ((responseLine = br.readLine()) != null) {
            response.append(responseLine.trim());
        }

        return mapper.readValue(response.toString(), new TypeReference<List<Transaction>>() {});
    }

}

