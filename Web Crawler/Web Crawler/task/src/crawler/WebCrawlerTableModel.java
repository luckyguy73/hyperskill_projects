package crawler;

import javax.swing.table.AbstractTableModel;
import java.util.*;

public class WebCrawlerTableModel extends AbstractTableModel {

    private final List<String> urls;
    private final List<String> titles;
    private final String[] tableHeaders = {"URL", "Title"};

    public WebCrawlerTableModel(List<String> urls, List<String> titles) {
        super();
        this.urls = urls;
        this.titles = titles;
    }

    @Override
    public int getRowCount() {
        return urls.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getValueAt(int row, int col) {
        return col == 0 ? urls.get(row) : titles.get(row);
    }

    @Override
    public String getColumnName(int columnIndex) {
        return tableHeaders[columnIndex];
    }

}
