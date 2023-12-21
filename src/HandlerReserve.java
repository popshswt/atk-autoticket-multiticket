import Model.CheckBooking.CheckBookingRequest;
import Model.CheckBooking.CheckBookingResponse;
import Model.HandlerReserve.HandlerReserveRequest;
import Model.HandlerReserve.HandlerReserveResponse;
import Model.HandlerReserve.SeatTo;
import com.google.gson.Gson;
import lombok.Data;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;

public class HandlerReserve {
    Gson gson = new Gson();
    HandlerReserveRequest handlerReserveRequest;
    HandlerReserveResponse handlerReserveResponse;
    CheckBookingRequest checkBookingRequest;
    CheckBookingResponse checkBookingResponse;


    public void handlerReserveBooking(String performId, String roundId, String zoneId, SeatTo seatTo, String token) {
        try {
            //reserve booking
            handlerReserveRequest = new HandlerReserveRequest(performId, roundId, zoneId, zoneId, seatTo, Collections.singletonList(null));
            HttpRequest handlerReserveRequestBody = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.allticket.com/booking/handler-reserve"))
                    .header("Authorization", token)
                    .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(handlerReserveRequest).toString()))
                    .build();
            HttpResponse<String> handlerReserveResponseBody = HttpClient.newHttpClient().send(handlerReserveRequestBody, HttpResponse.BodyHandlers.ofString());
            handlerReserveResponse = gson.fromJson(handlerReserveResponseBody.body(), HandlerReserveResponse.class);
            System.out.println("โซน " + zoneId + " ที่นั่ง " + seatTo.getSeats().get(0) + " และ "
                    + seatTo.getSeats().get(1) + " ว่างอยู่ กำลังจอง...");

            //Thread.sleep(5000);

            //check booking
            checkBookingRequest = new CheckBookingRequest(handlerReserveResponse.getData().getUuid());
            HttpRequest checkBookingRequestBody = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.allticket.com/booking/check-booking"))
                    .header("Authorization", token)
                    .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(checkBookingRequest).toString()))
                    .build();
            HttpResponse<String> checkBookingResponseBody = HttpClient.newHttpClient().send(checkBookingRequestBody, HttpResponse.BodyHandlers.ofString());
            checkBookingResponse = gson.fromJson(checkBookingResponseBody.body(), CheckBookingResponse.class);

            if ("100".equalsIgnoreCase(checkBookingResponse.getCode())) {
                throw new Exception("โซน " + zoneId + " ที่นั่ง " + seatTo.getSeats().get(0) + " และ "
                        + seatTo.getSeats().get(1) + " ทำการจองสำเร็จ!!");
            } else if ("51002".equalsIgnoreCase(checkBookingResponse.getCode())) {
                throw new Exception("ทำรายการเร็วเกินไป");
            } else if ("51000".equalsIgnoreCase(checkBookingResponse.getCode())) {
                throw new Exception("มีตั๋วในระบบเกิน 4 ใบ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
