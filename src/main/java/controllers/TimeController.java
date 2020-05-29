package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.Date;
import java.util.TimeZone;

@Controller
@RequestMapping("/")
public class TimeController {

    @GetMapping
    public ModelAndView showForm() {
        return new ModelAndView("index");
    }

    @PostMapping()
    public ModelAndView getTimeByTimezone(@RequestParam(name = "city", required = false, defaultValue = "Asia/Ho_Chi_Minh") String city) {
        ModelAndView modelAndView = new ModelAndView("index");
        Date date = new Date();
        TimeZone locale = TimeZone.getTimeZone(city);
        TimeZone local = TimeZone.getDefault();
        long locale_time = date.getTime() + (locale.getRawOffset() - local.getRawOffset());
        date.setTime(locale_time);
        modelAndView.addObject("city", city);
        modelAndView.addObject("date", date);
        return modelAndView;
    }
}
