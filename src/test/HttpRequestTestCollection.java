package test;

import edu.uic.http.*;
import edu.uic.model.ImageZipArchive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class HttpRequestTestCollection {

    private Collection<HttpTest> testCollection;

    public HttpRequestTestCollection() {
        System.out.println("HTTP REQUEST TEST COLLECTION\n" );
        testCollection = new ArrayList<>();
        testCollection.add(new HttpGetTest("/image/"));
        testCollection.add(new HttpGetTest("/image/unixtime/").addParam("gid_list", Arrays.asList(1, 2, 3, 35, 4, 5, 6, 1020)));
        testCollection.add(new HttpPostTest("/image/").addParam("image_zip_archive", new ImageZipArchive("/home/alessandro/giraffe_api_upload_test_alessandro.zip")));
    }

    public void runTests() {
        for(HttpTest t : testCollection) {
            t.execute();
        }
        System.out.print("\n\n");
    }

    private static void printTest(HttpTest test) {
        System.out.println("TEST: " + test.getTestType());
        System.out.println("Call path: " + test.getCallPath());
        System.out.println("Params: " + formatParams(test.getParams()));
    }

    private static String formatParams(List<Parameter> params) {
        StringBuilder formattedParams = new StringBuilder("");
        for(int i=0; i<params.size(); i++) {
            formattedParams.append(params.get(i).getName() + "=" + params.get(i).getValue());
            if (i != params.size()-1) {
                formattedParams.append("; ");
            }
        }
        return formattedParams.toString();
    }

    private static void printResponse(Response response) {
        System.out.println("Response Success: " + response.isSuccess());
        System.out.println("Response Content: " + response.getContent().toString());
    }

    private class HttpGetTest implements HttpTest {

        private String callPath;
        private List<Parameter> params;

        public HttpGetTest(String callPath) {
            this.callPath = callPath;
            params = new ArrayList<>();
        }

        public String getCallPath() {
            return callPath;
        }

        public List<Parameter> getParams() {
            return params;
        }

        public String getTestType() {
            return this.getClass().getSimpleName();
        }

        public HttpGetTest addParam(String name, String value) {
            params.add(new Parameter(name, value));
            return this;
        }

        public HttpGetTest addParam(String name, int value) {
            params.add(new Parameter(name, value));
            return this;
        }

        public HttpGetTest addParam(String name, List<Integer> values) {
            params.add(new Parameter(name, values));
            return this;
        }

        public HttpGetTest addParam(String name, ImageZipArchive imgZip) {
            params.add(new Parameter(name, imgZip));
            return this;
        }

        @Override
        public void execute() {
            printTest(this);

            try {
                if (params.size() > 0) {
                    printResponse(new Request(RequestMethod.GET, callPath, new ParametersList(params)).execute());
                }
                else {
                    printResponse(new Request(RequestMethod.GET, callPath).execute());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class HttpPostTest implements HttpTest {

        private String callPath;
        private List<Parameter> params;

        public HttpPostTest(String callPath) {
            this.callPath = callPath;
            params = new ArrayList<>();
        }

        public String getCallPath() {
            return callPath;
        }

        public List<Parameter> getParams() {
            return params;
        }

        public String getTestType() {
            return this.getClass().getSimpleName();
        }

        public HttpPostTest addParam(String name, String value) {
            params.add(new Parameter(name, value));
            return this;
        }

        public HttpPostTest addParam(String name, int value) {
            params.add(new Parameter(name, value));
            return this;
        }

        public HttpPostTest addParam(String name, List<Integer> values) {
            params.add(new Parameter(name, values));
            return this;
        }

        public HttpPostTest addParam(String name, ImageZipArchive imgZip) {
            params.add(new Parameter(name, imgZip));
            return this;
        }

        @Override
        public void execute() {
            printTest(this);

            try {
                if (params.size() > 0) {
                    printResponse(new Request(RequestMethod.POST, callPath, new ParametersList(params)).execute());
                }
                else {
                    printResponse(new Request(RequestMethod.POST, callPath).execute());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.print("\n");
        }
    }

}

