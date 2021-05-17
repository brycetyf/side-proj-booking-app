package bryce.sideproject.controller;

import bryce.sideproject.dao.BookingDao;
import bryce.sideproject.model.Booking;
import bryce.sideproject.model.Excepetion;
import bryce.sideproject.model.ResponseDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class BookingController {

    @Autowired
    BookingDao bookingDao;

    ObjectMapper objectMapper = new ObjectMapper();
    @GetMapping ("/api/getall")
    @ResponseBody
    public List<Booking> getAllBooking(){
        return bookingDao.findAll();
    }
    
    @RequestMapping("/api/addnewbooking")
    @ResponseBody
    public ResponseEntity addNewBooking(@RequestBody Booking newBooking){
        try{
            bookingDao.save(newBooking);
            ResponseDetails responseDetails = new ResponseDetails(new Date(), "saved booking into DB",
                    "save success");
            return new ResponseEntity(responseDetails, HttpStatus.OK);
        }
        catch(Excepetion e){
            ResponseDetails responseDetails = new ResponseDetails(new Date(), "failed to save booking into DB",
                    "save failed");
            return new ResponseEntity(responseDetails, HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping("/api/getbooking/{bid}")
    @ResponseBody
    public Optional<Booking> getBooking(@PathVariable("bid") int bid){
        try{
            Optional<Booking> booking = bookingDao.findById(bid);
            return booking;
        }
        catch(Excepetion e){
            return null;
        }
    }

    @RequestMapping("/api/deletebooking/{bid}")
    @ResponseBody
    public ResponseEntity deleteBooking(@PathVariable("bid") int bid){
        try{
            bookingDao.deleteById(bid);
            ResponseDetails responseDetails = new ResponseDetails(new Date(), "deleted booking from DB",
                    "delete success");
            return new ResponseEntity(responseDetails, HttpStatus.OK);
        }
        catch(Exception e){
            ResponseDetails responseDetails = new ResponseDetails(new Date(), "failed to delete booking from DB",
                    "delete failed");
            return new ResponseEntity(responseDetails, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/api/updatetiming/{bid}")
    @ResponseBody
    public ResponseEntity updateTiming(@PathVariable("bid") int bid, @RequestBody Booking newBooking){
        try{
            String newDate = newBooking.getDate();
            bookingDao.updateTiming(bid,newDate);
            ResponseDetails responseDetails = new ResponseDetails(new Date(), "updated timing in DB",
                    "update success");
            return new ResponseEntity(responseDetails, HttpStatus.OK);
        }
        catch(Excepetion e){
            ResponseDetails responseDetails = new ResponseDetails(new Date(), "failed to update timing in DB",
                    "update failed");
            return new ResponseEntity(responseDetails, HttpStatus.BAD_REQUEST);
        }
    }

}
