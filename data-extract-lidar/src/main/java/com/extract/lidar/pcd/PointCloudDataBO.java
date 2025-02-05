package com.extract.lidar.pcd;

import java.util.ArrayList;

public class PointCloudDataBO {

    private final String DEFAULT_VERSION = ".7";

    private final String DEFAULT_VIEW_POINT = "0 0 0 1 0 0 0";

    private final String DEFAULT_DATA_STORAGE_TYPE = "DATA ascii";

    private final String DEFAULT_FIELDS = "frameId timestamp-s timestamp-us lutindex flag azimuth distance intensity reflectivity azimuthidx0 azimuthidx1";

    private final String DEFAULT_SIZE = "2 4 4 1 2 2 2 2 1 2 2";

    private final String DEFAULT_TYPE = "U U U U U F F F U U U";

    private final String DEFAULT_COUNT = "1 1 1 1 1 1 1 1 1 1 1";
    private String version = DEFAULT_VERSION;
    private String fields = DEFAULT_FIELDS;

    private String size = DEFAULT_SIZE;

    private String type = DEFAULT_TYPE;

    private String count = DEFAULT_COUNT;

    private Integer width;

    private Integer height = 1;

    private String viewPoint = DEFAULT_VIEW_POINT;

    private Integer points;

    private String dataStorageType = DEFAULT_DATA_STORAGE_TYPE;

    private ArrayList<ArrayList<String>> dataList;

    public PointCloudDataBO(Integer width, ArrayList<ArrayList<String>> dataList) {
        this.width = width;
        this.points = width;
        this.dataList = dataList;
    }

    public StringBuilder headerContent() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("VERSION ")
                .append(version)
                .append("\n")
                .append("FIELDS ")
                .append(fields)
                .append("\n")
                .append("SIZE ")
                .append(size)
                .append("\n")
                .append("TYPE ")
                .append(type)
                .append("\n")
                .append("COUNT ")
                .append(count)
                .append("\n")
                .append("WIDTH ")
                .append(width)
                .append("\n")
                .append("HEIGHT ")
                .append(height)
                .append("\n")
                .append("VIEWPOINT ")
                .append(viewPoint)
                .append("\n")
                .append("POINTS ")
                .append(points)
                .append("\n")
                .append(dataStorageType)
                .append("\n");

        return stringBuilder;
    }


    public StringBuilder dataContent() {
        StringBuilder stringBuilder = new StringBuilder();
        for (ArrayList<String> row : dataList) {
            for (int i = 0; i < row.size(); i++) {
                stringBuilder.append(row.get(i));
                if (i < row.size() - 1) {
                    stringBuilder.append(" ");
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder;
    }


}
