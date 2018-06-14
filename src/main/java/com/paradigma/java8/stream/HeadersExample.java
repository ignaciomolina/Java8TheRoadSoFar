package com.paradigma.java8.stream;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class HeadersExample {

  private static Map<String, List<String>> generateHeaders() {
    List<String> valuesHeader1 = Arrays.asList("value1.1", "value1.2", "value1.3");
    List<String> valuesHeader2 = Arrays.asList("value2.1");
    List<String> valuesHeader3 = Arrays.asList("value3.1", "value3.2");

    Map<String, List<String>> headers = new LinkedHashMap<>();
    headers.put("header1", valuesHeader1);
    headers.put("header2", valuesHeader2);
    headers.put("header3", valuesHeader3);

    return headers;
  }

  /*
  From

    <header1 = {value1, value2, value3},
     header2 = {value1},
     header3 = {value1, value2}>

  to

    [header1, value1, header1, value2, header1, value3, header2, value1, header3, value1, header3, value2]
   */
  public static void main(String[] args) {

    Map<String, List<String>> headersMap = generateHeaders();

    String[] headers = headersMap.entrySet().stream()
            .map(entry -> entry.getValue().stream()
                    .map(value -> new String[]{entry.getKey(), value})
                    .flatMap(Arrays::stream))
            .flatMap(Function.identity())
            .toArray(String[]::new);

    System.out.println(headersMap);
    System.out.println(Arrays.asList(headers));
  }
}
