package crawler;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.List;
import java.util.regex.*;
import java.util.regex.Pattern;

public class WebCrawler extends JFrame {

    private final List<String> urls = new ArrayList<>();
    private final List<String> titles = new ArrayList<>();
    private final WebCrawlerTableModel model = new WebCrawlerTableModel(urls, titles);
    private final JTable table = new JTable(model);
    private final JTextField textField = new JTextField(40);
    private final JButton runButton = new JButton("Parse");
    private final JLabel titleLabel = new JLabel();

    public WebCrawler() {
        setFieldSettings();
        setPanels();
        initialize();
    }

    private void setFieldSettings() {
        table.setEnabled(false);
        table.setName("TitlesTable");
        textField.setName("UrlTextField");
        titleLabel.setName("TitleLabel");
        runButton.setName("RunButton");
        runButton.addActionListener(a -> buttonAction());
    }

    private void buttonAction() {
        final String url = textField.getText();
        System.out.println("URL: " + url);
        String siteText = "";
        try (InputStream inputStream = new BufferedInputStream(new URL(url).openStream())) {
            siteText = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        }  catch (Exception e) { System.out.println(e.getMessage()); }
        titleLabel.setText(parseTitle(siteText));
        addOriginalUrlToTable(url);
        addUrls(siteText);
        addTitles();
        deleteMissingTitles();
        model.fireTableDataChanged();
    }

    private void setPanels() {
        Container pane = this.getContentPane();
        pane.removeAll();
        pane.setLayout(new GridBagLayout());
        this.getRootPane().setDefaultButton(runButton);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(0, 5, 0, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;
        pane.add(new JLabel("URL: "), c);
        c.gridx = 1;
        c.gridy = 0;
        pane.add(textField, c);
        c.gridx = 2;
        c.gridy = 0;
        pane.add(runButton, c);
        c.gridx = 0;
        c.gridy = 1;
        pane.add(new JLabel("Title: "), c);
        c.gridx = 1;
        c.gridy = 1;
        pane.add(titleLabel, c);
        c.weightx = 1;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 2;
        pane.add(getScrollPane(), c);
//        if (showTable) {
//            c.weightx = 1;
//            c.gridwidth = 3;
//            c.gridx = 0;
//            c.gridy = 2;
//            pane.add(getScrollPane(), c);
//        }
    }

    private JScrollPane getScrollPane() {
        table.setEnabled(false);
        JScrollPane scroller = new JScrollPane(table);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        return scroller;
    }

    private void initialize() {
        setTitle("Web Crawler");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private String parseTitle(String text) {
        Pattern pattern = Pattern.compile("<title>(.+)</title>");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "Not Found";
        }
    }

    private void addOriginalUrlToTable(String url) {
        urls.clear();
        urls.add(url);
    }

    private void addUrls(String siteText) {
        Pattern pattern = Pattern.compile("(?i)<a\\s+(?:[^>]*?\\s+)?href=([\"'])(.*?)\\1");
        Matcher matcher = pattern.matcher(siteText);
        String initialUrl = urls.get(0);
        String protocol = initialUrl.substring(0, initialUrl.indexOf("//"));
        StringBuilder url;
        while (matcher.find()) {
            url = new StringBuilder();
            String text = matcher.group(2);
            if (text.startsWith("#")) {
                continue;
            }
            if (!text.startsWith("http")) {
                if (text.startsWith("//")) {
                    url.append(protocol).append(text);
                } else if (text.startsWith("/")) {
                    url.append(initialUrl).append(text);
                } else {
                    url.append(initialUrl).append("/").append(text);
                }
            } else {
                url.append(text);
            }
            urls.add(url.toString());
        }
        System.out.printf("added %d urls from %s\n", urls.size(), urls.get(0));
    }

    private void addTitles() {
        titles.clear();
        String siteText = "";
        for (String url : urls) {
            try {
                URLConnection conn = new URL(url).openConnection();
                int code = ((HttpURLConnection)conn).getResponseCode();
                if (code == 204 || !conn.getContentType().startsWith("text/html")) {
                    titles.add("Not Found");
                    continue;
                }
                try (InputStream is = new BufferedInputStream(conn.getInputStream())){
                    siteText = new String(is.readAllBytes(), StandardCharsets.UTF_8);
                }
            } catch (IOException e) { System.out.println(e.getMessage()); }
            titles.add(parseTitle(siteText));
        }
        System.out.printf("added %d titles from %s\n", titles.size(), titles.get(0));
    }


    private void deleteMissingTitles() {
        int j = 0;
        for (int i = 0; i < urls.size(); ++i) {
            if (!titles.get(i).equals("Not Found")) {
                urls.set(j, urls.get(i));
                titles.set(j++, titles.get(i));
            }
        }
        updateList(j, urls);
        updateList(j, titles);
    }

    private void updateList(int j, List<String> list) {
        List<String> t = new ArrayList<>(list);
        list.clear();
        list.addAll(t.subList(0, j));
    }

}
