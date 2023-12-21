import Model.CheckBooking.CheckBookingRequest;
import Model.CheckBooking.CheckBookingResponse;
import Model.GetSeat.GetSeatRequest;
import Model.GetSeat.GetSeatResponse;
import Model.GetSeat.Seat;
import Model.HandlerReserve.HandlerReserveRequest;
import Model.HandlerReserve.HandlerReserveResponse;
import Model.HandlerReserve.SeatTo;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String performUri = "2024ROWOONFANMEETING";
        String performId = new GetPerformId().getPerformIdFromUri(performUri);
        String roundId = "R1";
        String zoneId = "A1";
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InBvcF9sb3ZlX2x5X2RheUBob3RtYWlsLmNvbSIsInVybGJhY2siOiJ3d3cuYWxsdGlja2V0LmNvbSIsInBheW1lbnRDaGFubmVsIjoiQzA3IiwidGlja2V0VHlwZSI6IjAxIiwibGFuZyI6IkUiLCJkYXRhIjoiN2NjMjhlODUzYzNkNzZkMDRkYWQzNGMwNTFhYjYwMDM3YzU0YmJjNzI3ZjU0ODkyNjE4MmM5ZDljN2YzOWNlNGViOTM5ZDUzMzc4NzJiODlkNmY2OTk3ZDc2N2RjMzMwNTFlYzY3NTE1OGY0ZTdlMjZlZDI2OGRkMWQ3OTRjYWYxOGJhZTJjODk5NzZkNzczOTBiYmY2MmYzMjE1MGVmYmZhYTE0ZjEzYzA2MTQxY2IyZGY5MmE1ODAxYjE2ODhiOWE1NDJlOTYwMjU2OWViOWYxODk3MTYzODU4OGEwMDIzZTNjMjczNGIyN2UwMDJjZTY4YjMzMTc0N2NmN2QzOTUyMDZmYzY5MGU1ZjNmZjFmYTVkYjcyOTI1MjQwY2I1YmFkYmJjYzkxMGExYjc3ZTNhNmE0NmRjOWJiM2U5NThkZGZhNzZiNGZlMjBhZThkMjEyOTlkYzI4NTIwZmE4ZDEwNDZlMTc3Zjg5Njc2NWRiOGQ2ZmIwOGIzNDAzZjc4YzE5ZWI2MDY4NTZiMWFlYTU3NGU1NjY0MTczMWYzZGUzMzM1NWQ5MGM5NjU5MzI0MGI5NGE1NTllNDQyYjBjZjZiOWExY2M2MWYyNmI2NjQ2ZjQyZDBmOGY1OGFjZTBlZTg3MmYyNzU0OWI5ZmNiMTc3MDJkZmVmNTI5MzMyNzM2ZjhiNzM3ODVlYTRkNTY4YzAyNTUxZTNkYzFiYTQ3OTE1OGNlYjliNzFlODNmMDljNzYwNDgzYjU5ZDRhZDg1ZmZlYjRmOTA2ZjQ5NjM3Mjk1MzMzNmE0YTNjN2Q5ZWUxYzc0ZTk1ZTYzMTQ5N2E3NzYxZTNiZjU1YjFlZDc0Nzg3NDIwZjUyNzgzYWEyM2JkYmI2MWMyMGNmNDYyZDQxNmMzMjYxYTFiY2ZiMmUyZTlhN2U1NzhlNzY3ZGE1Mjc1NDg5OGVjNmZhYWZiODFjODAxMGQzN2ZjYTBhNDQ5NGUwOGViOWVkN2M4YThlZjk3ZjU0ZDM3NDc5ODQ4MmUwMzBmMDFjYzY1YThlMmIyYThlNTE5YmE2ZmI4MzAyZjk0YzE2ZTZmMDA4YTdjMTNkNmJkZDc1MTcwMTEwMWI1MWVmMWM3M2I1NWRmMWU3ZDUwNzg3MGQ3ZDkxN2Y5N2M2M2Y2NGQ4NmI3Mjk5ZmRiOWNmMzE4ZWRmNTY1NGExOTI4MjgwYWM4OGE0MzBkN2UyYTI4NzUwNGZmY2FhZGQ4ZiIsInRpbWVTdGFtcCI6MC4zODUwNDI2MDUzNTUyNzMxLCJpYXQiOjE3MDMxMjc2ODUsImV4cCI6MTcwMzEzODQ4NSwiaXNzIjoiY3NhdGsxOCJ9.2Uc_HsCSHJt4RXWi_ou_ad_xAYQGZy0cBUT1eGICO2M";

        while ("99998".equalsIgnoreCase(performId)) {
            System.out.println("ยังไม่ใช่ performId ของจริง");
            performId = new GetPerformId().getPerformIdFromUri(performUri);
        }

        Gson gson = new Gson();

        GetSeatRequest getSeatRequest = new GetSeatRequest(performId, roundId, zoneId);
        GetSeatResponse getSeatResponse;

        while (true) {
            try {
                HttpRequest getSeatRequestBody = HttpRequest.newBuilder()
                        .uri(URI.create("https://api.allticket.com/booking/get-seat"))
                        .header("Authorization", token)
                        .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(getSeatRequest).toString()))
                        .build();

                HttpResponse<String> getSeatResponseBody = HttpClient.newHttpClient().send(getSeatRequestBody, HttpResponse.BodyHandlers.ofString());
                getSeatResponse = gson.fromJson(getSeatResponseBody.body(), GetSeatResponse.class);

                List<Seat> seatList = getSeatResponse.getData().getSeats_available().get(0).getSeat();
                HandlerReserveRequest handlerReserveRequest;
                HandlerReserveResponse handlerReserveResponse;
                CheckBookingRequest checkBookingRequest;
                CheckBookingResponse checkBookingResponse;
                SeatTo seatTo;

                for (int i = 0; i < seatList.size(); i++) {
                    List<String> seats = new ArrayList<>();
                    if ("A".equalsIgnoreCase(seatList.get(i).getStatus())) {
                        if ("1".equalsIgnoreCase(seatList.get(i).getSeatNo())) {
                            if ("A".equalsIgnoreCase(seatList.get(i+1).getStatus())) {
                                seats.add(seatList.get(i).getRowName() + "_" + seatList.get(i).getSeatNo());
                                seats.add(seatList.get(i+1).getRowName() + "_" + seatList.get(i+1).getSeatNo());
                                seatTo = new SeatTo(getSeatResponse.getData().getSeats_available().get(0).getZoneSeatLabel(), seats);
                            }
                        } else if (i == seatList.size()-1) {
                            if ("A".equalsIgnoreCase(seatList.get(i-1).getStatus())) {
                                seats.add(seatList.get(i).getRowName() + "_" + seatList.get(i).getSeatNo());
                                seats.add(seatList.get(i-1).getRowName() + "_" + seatList.get(i-1).getSeatNo());
                                seatTo = new SeatTo(getSeatResponse.getData().getSeats_available().get(0).getZoneSeatLabel(), seats);
                            }
                        } else {
                            if ("A".equalsIgnoreCase(seatList.get(i+1).getStatus())) {
                                seats.add(seatList.get(i).getRowName() + "_" + seatList.get(i).getSeatNo());
                                seats.add(seatList.get(i+1).getRowName() + "_" + seatList.get(i+1).getSeatNo());
                                seatTo = new SeatTo(getSeatResponse.getData().getSeats_available().get(0).getZoneSeatLabel(), seats);
                            } else if ("A".equalsIgnoreCase(seatList.get(i-1).getStatus())) {
                                seats.add(seatList.get(i).getRowName() + "_" + seatList.get(i).getSeatNo());
                                seats.add(seatList.get(i-1).getRowName() + "_" + seatList.get(i-1).getSeatNo());
                                seatTo = new SeatTo(getSeatResponse.getData().getSeats_available().get(0).getZoneSeatLabel(), seats);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}