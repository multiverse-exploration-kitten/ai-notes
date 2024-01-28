package com.abx.ainotebook.openai;

public class OpenAiExample {
    //    public static void main(String[] args) {
    //        String token = System.getenv("OPENAI_TOKEN");
    //        OpenAiService service = new OpenAiService(token, Duration.ofSeconds(30));
    //        GptService gptService = new GptService(service);
    //        Scanner keyboard = new Scanner(System.in);
    //
    //        //        GPT help user answer questions
    //        String question = keyboard.nextLine();
    //        List<CompletionChoice> results =
    //                gptService.complete("Can you write the answers to the following questions? " + question);
    //        String ans = "";
    //        for (CompletionChoice choice : results) {
    //            ans += gptService.parseGptResponse(choice);
    //        }
    //
    //        //        GPT help user make summary
    //        String note = keyboard.nextLine();
    //        List<CompletionChoice> summary = gptService.complete(
    //                "Could you please generate a TLDR (Too Long Didn't Read) summary of the following notes? " +
    // note);
    //        String summaryContent = "";
    //        for (CompletionChoice choice : summary) {
    //            summaryContent += gptService.parseGptResponse(choice);
    //        }
    //    }
}
