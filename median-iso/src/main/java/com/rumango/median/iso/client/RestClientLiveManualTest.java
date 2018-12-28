//package com.rumango.median.iso.client;
//
//import java.io.IOException;
//
//public class RestClientLiveManualTest {
// 
//   // @Test(expected = SSLPeerUnverifiedException.class)
//    public void whenHttpsUrlIsConsumed_thenException() 
//      throws ClientProtocolException, IOException {
//  
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        String urlOverHttps
//          = "https://localhost:8082/spring-security-rest-basic-auth";
//        HttpGet getMethod = new HttpGet(urlOverHttps);
//         
//        HttpResponse response = httpClient.execute(getMethod);
//        assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
//    }
//}