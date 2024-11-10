//package com.walla_ho.screenmatch.service;
//
//import com.theokanning.openai.completion.CompletionRequest;
//import com.theokanning.openai.service.OpenAiService;
//
//public class QueryChatGPT {
//    public static String getWatch(String text) {
//        OpenAiService service = new OpenAiService(System.getenv("OPENAI_APIKEY"));
//
//        CompletionRequest request = CompletionRequest.builder()
//                .model("gpt-3.5-turbo-instruct") //model compatibility
//                .prompt("Where can I watch the following series: " + text) //ask
//                .maxTokens(1000) //response length
//                .temperature(0.7) //modification between the solicitation
//                .build();
//
//        var answer = service.createCompletion(request);
//        return answer.getChoices().get(0).getText();
//    }
//}
