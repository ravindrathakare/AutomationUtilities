package andoidreports.pojo;
public class HTMLReportContants {
    public static String CHROME_DRIVER_PATH = "/Users/<username>/Uber/chromedriver";
    public static String TEST_ANALYZER = "https://test-analyzer.uberinternal.com/test-case-results?testCaseName=%s";
    public static String TEST_CASE_LIST = "TestCases.txt";

    public static String SCRIPT="  <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script> <script type=\"text/javascript\">\n" +
            "        google.charts.load(\"current\", {packages:[\"corechart\"]});\n" +
            "        google.charts.setOnLoadCallback(drawChart);\n" +
            "        function drawChart() {\n" +
            "            var data = google.visualization.arrayToDataTable([['Task','Execution'],['PASS',%s], ['FAIL',%s],['ERROR',%s],]);\n" +
            "\n" +
            "            var options = {\n" +
            "                title: '%s',\n" +
            "                is3D: true,\n" +
            "                colors: ['#19e63d', '#e61725','#1c91c0']\n" +
            "            };\n" +
            "\n" +
            "            var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));\n" +
            "            chart.draw(data, options);\n" +
            "        }\n" +
            " function sortTable(n) {\n" +
            "            var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;\n" +
            "            table = document.getElementById(\"result\");\n" +
            "            switching = true;\n" +
            "            //Set the sorting direction to ascending:\n" +
            "            dir = \"asc\";\n" +
            "            /*Make a loop that will continue until\n" +
            "            no switching has been done:*/\n" +
            "            while (switching) {\n" +
            "                //start by saying: no switching is done:\n" +
            "                switching = false;\n" +
            "                rows = table.rows;\n" +
            "                /*Loop through all table rows (except the\n" +
            "                first, which contains table headers):*/\n" +
            "                for (i = 1; i < (rows.length - 1); i++) {\n" +
            "                    //start by saying there should be no switching:\n" +
            "                    shouldSwitch = false;\n" +
            "                    /*Get the two elements you want to compare,\n" +
            "                    one from current row and one from the next:*/\n" +
            "                    x = rows[i].getElementsByTagName(\"TD\")[n];\n" +
            "                    y = rows[i + 1].getElementsByTagName(\"TD\")[n];\n" +
            "                    /*check if the two rows should switch place,\n" +
            "                    based on the direction, asc or desc:*/\n" +
            "                    if (dir == \"asc\") {\n" +
            "                        if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {\n" +
            "                            //if so, mark as a switch and break the loop:\n" +
            "                            shouldSwitch= true;\n" +
            "                            break;\n" +
            "                        }\n" +
            "                    } else if (dir == \"desc\") {\n" +
            "                        if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {\n" +
            "                            //if so, mark as a switch and break the loop:\n" +
            "                            shouldSwitch = true;\n" +
            "                            break;\n" +
            "                        }\n" +
            "                    }\n" +
            "                }\n" +
            "                if (shouldSwitch) {\n" +
            "                    /*If a switch has been marked, make the switch\n" +
            "                    and mark that a switch has been done:*/\n" +
            "                    rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);\n" +
            "                    switching = true;\n" +
            "                    //Each time a switch is done, increase this count by 1:\n" +
            "                    switchcount ++;\n" +
            "                } else {\n" +
            "                    /*If no switching has been done AND the direction is \"asc\",\n" +
            "                    set the direction to \"desc\" and run the while loop again.*/\n" +
            "                    if (switchcount == 0 && dir == \"asc\") {\n" +
            "                        dir = \"desc\";\n" +
            "                        switching = true;\n" +
            "                    }\n" +
            "                }\n" +
            "            }\n" +
            "        }"+
            "    </script>";

    public static String STYLE =" <style>\n" +
            "    * {font-family: Helvetica Neue, Arial, sans-serif; }\n" +
            "\n" +
            "    body { background-image: linear-gradient(rgba(255, 152, 0, 0) 25%, rgba(255, 255, 255, 0));}\n" +
            "\n" +
            "    h1, table { text-align: center; }\n" +
            "\n" +
            "    table {border-collapse: collapse;  width: 100%; margin: 0 auto 5rem;}\n" +
            "\n" +
            "    th, td { padding: 1.5rem; font-size: 0.9rem; }\n" +
            "\n" +
            "    tr {background: hsla(181, 89%, 38%, 0.58); }\n" +
            "\n" +
            "    tr, td { transition: .4s ease-in; }\n" +
            "\n" +
            "    tr:first-child {background: hsla(122, 52%, 69%, 0.46); }\n" +
            "\n" +
            "    tr:nth-child(even) { background: hsla(43, 12%, 89%, 0.7); }\n" +
            "\n" +
            "    td:empty {background: hsla(50, 25%, 60%, 0.7); }\n" +
            "\n" +
/*            "    tr:hover:not(#firstrow), tr:hover td:empty {background: #ff0; pointer-events: visible;}\n" +
            "    tr:hover:not(#firstrow) { transform: scale(1.2); font-weight: 700; box-shadow: 0px 3px 7px rgba(0, 0, 0, 0.5);}\n" +*/
            "    </style>";

    public static String HTML="<html>%s</html>";
    public static String BODY="<body>%s</body>";
    public static String HEAD="<head>%s</head>";
    public static String TR="<tr>%s</tr>";
    public static String TABLE="<table id=\"result\">%s</table>";
    public static String DIV = "<div>%s</div>";
    public static String PIECHART_DIV = "<div id=\"piechart_3d\" style=\"width: 900px; height: 500px; margin:0 auto;\"></div>";
    public static String HREF = "<a href=\"%s\">%s</a>";
    public static String HREF_REPORT= "<a href=\"%s\"></a>";


    public static String TH_TESTNAME="<th align=\"left\" >TEST CASE NAME</th>";
    public static String TH_STATUS="<th align=\"left\" onclick=sortTable(1)> STATUS</th>";
    public static String TH_PASS_COUNT="<th align=\"left\" > PASS COUNT</th>";
    public static String TH_FAIL_COUNT="<th align=\"left\"> FAIL COUNT</th>";
    public static String TH_ERROR_COUNT="<th align=\"left\"> ERROR COUNT</th>";
    public static String TH_BUILD="<th align=\"left\" > BUILD </th>";
    public static String TH_DATE="<th align=\"left\" onclick=sortTable(0)> DATE AND TIME </th>";


    public static String TD_TESTNAME="<td align=\"left\">%s</td>";
    public static String TD_STATUS="<td align=\"left\">%s</td>";
    public static String TD_BUILD="<td align=\"left\" >%s</td>";
    public static String TD_DATE="<td align=\"left\" >%s</td>";
}
