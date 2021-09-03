package com.sojern.api;

import com.sojern.models.Data;
import com.sojern.models.DataUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Arrays;

@RestController
public class MainRestController {
    private static final Logger logger = LogManager.getLogger(MainRestController.class);

    @GetMapping("/min")
    public String min(@Valid Data input, BindingResult error) {
        logger.info("min request received");
        logger.debug("data list provided {}",input.getList());
        logger.debug("Quantifier provided {}",input.getList());

        if (error.hasErrors()) {
            logger.error("API call with list: " + input.getList() + " and q:" + input.getQ());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Input parameters bad!");
        }
        DataUtility format = new DataUtility();
        Double[] list = format.convertToList(input.getList());
        double q = format.convertToQuantifier(input.getQ());
        if (q > list.length) {
            logger.error("Check your quantifier"
                    + "list: " + input.getList() + " and q:" + input.getQ());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, " Check your quantifier");
        }
        try {
            if (q == 0)
                return "MinList: ";
            StringBuffer result = new StringBuffer();
            Arrays.sort(list);
            for (int i = 0; i < q; i++) {
                result.append(list[i]);
                result.append(" , ");
            }
            result.setLength(result.length() - 3);
            logger.info("Response result {}",result);
            return "MinList: " + result;
        } catch (Exception ex) {
            logger.error("error in processing min");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, " min api not working");
        }
    }

    @GetMapping("/max")
    public String max(@Valid Data input, BindingResult error) {
        logger.info("Max request received");
        logger.debug("data list provided {}",input.getList());
        logger.debug("Quantifier provided {}",input.getList());

        if (error.hasErrors()) {
            logger.error("API call with list: " + input.getList() + " and q:" + input.getQ());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Input parameters bad!");
        }
        DataUtility format = new DataUtility();
        Double[] list = format.convertToList(input.getList());

        double q = format.convertToQuantifier(input.getQ());
        if (q > list.length) {
            logger.error("Check your quantifier"
                    + "list: " + input.getList() + " and q:" + input.getQ());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, " Check your quantifier");
        }
        try {
            if (q == 0)
                return "MaxList: ";
            StringBuffer result = new StringBuffer();
            Arrays.sort(list,(a,b)->b.compareTo(a));
            for (int i = 0; i < q; i++) {
                result.append(list[i]);
                result.append(" , ");
            }
            result.setLength(result.length() - 3);
            logger.info("Response result {}",result);
            return "MaxList: " + result;
        } catch (Exception ex) {
            logger.error("error in processing max");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, " max api not working");
        }
    }

    @GetMapping("/avg")
    public String avg(@Valid Data input, BindingResult error) {
        logger.info("Avg request received");
        logger.debug("data list provided {}",input.getList());

        if (error.hasErrors()) {
            logger.error("API call with list: " + input.getList() + " and q:" + input.getQ());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, " Input parameters bad!");
        }
        DataUtility format = new DataUtility();
        Double[] list = format.convertToList(input.getList());
        double q = format.convertToQuantifier(input.getQ());
        if (q != Integer.MAX_VALUE) {
            logger.error("Check your quantifier"
                    + "list: " + input.getList() + " and q:" + input.getQ());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, " No Quantifier for avg api");
        }
        try {
            Double mean = 0.0;
            for (int i = 0; i < list.length; i++) {
                mean = mean + list[i];
            }
            mean /= list.length;
            String result = String.format("%.3f", mean);
            logger.info("Response result {}",result);
            return "AvgList: " + result;
        } catch (Exception ex) {
            logger.error("error in processing avg");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, " error in avg api");
        }
    }

    @GetMapping("/median")
    public String median(@Valid Data input, BindingResult error) {
        logger.info("Median request received");
        logger.debug("data list provided {}",input.getList());

        if (error.hasErrors()) {
            logger.error("API call with list: " + input.getList() + " and q:" + input.getQ());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, " Input parameters bad!");
        }
        DataUtility format = new DataUtility();
        Double[] list = format.convertToList(input.getList());
        double q = format.convertToQuantifier(input.getQ());
        if (q != Integer.MAX_VALUE) {
            logger.error("Check your quantifier"
                    + "list: " + input.getList() + " and q:" + input.getQ());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, " No Quantifier for median api");
        }
        try {
            Arrays.sort(list);
            if (list.length % 2 == 0) {
                return "median:  " + ((list[list.length / 2] + list[(list.length / 2) - 1]) / 2.0);
            } else
                return "median: " + list[list.length / 2];
        } catch (Exception ex) {
            logger.error("error in processing median");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, " error in median api");
        }
    }


    @RequestMapping("/percentile")
    public String percentile(@Valid Data input, BindingResult error) {
        logger.info("Percentile request received");
        logger.debug("data list provided {}",input.getList());
        logger.debug("Quantifier provided {}",input.getList());

        if (error.hasErrors()) {
            logger.error("API call with list: " + input.getList() + " and q:" + input.getQ());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, " Input parameters bad!");
        }
        DataUtility format = new DataUtility();
        Double[] list = format.convertToList(input.getList());
        double q = format.convertToQuantifier(input.getQ());
        if (q > 100 && q<0) {
            logger.error("Check your quantifier"
                    + "list: " + input.getList() + " and q:" + input.getQ());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, " Check your quatifier");
        }
        try {
            double pointer = Math.ceil((q / 100) * list.length);
            Double result = list[(int)(pointer-1)];
            logger.info("Response result {}",result);
            return "Percentile: " + result;
        } catch (Exception ex) {
            logger.error("error in processing Percentile");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, " error in percentile api");
        }
    }


}