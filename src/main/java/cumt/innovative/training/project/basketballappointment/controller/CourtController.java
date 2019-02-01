package cumt.innovative.training.project.basketballappointment.controller;

import cumt.innovative.training.project.basketballappointment.config.ConfigurationModel;
import cumt.innovative.training.project.basketballappointment.model.Court;
import cumt.innovative.training.project.basketballappointment.model.Room;
import cumt.innovative.training.project.basketballappointment.model.User;
import cumt.innovative.training.project.basketballappointment.utils.ControllerHelper;
import cumt.innovative.training.project.basketballappointment.utils.auth.Authorization;
import cumt.innovative.training.project.basketballappointment.utils.db.TableDeleteHelper;
import cumt.innovative.training.project.basketballappointment.utils.db.TableInsertHelper;
import cumt.innovative.training.project.basketballappointment.utils.db.TableQueryHelper;
import cumt.innovative.training.project.basketballappointment.utils.db.TableUpdateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CourtController {
    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/api/court", method = RequestMethod.GET)
    @ResponseBody
    public List<Court> allCourts() {
        Authorization.verify(request);
        List<Court> courts = TableQueryHelper.query(Court.class);
        return courts;
    }

    @RequestMapping(value = "/api/court/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Court specifiedCourt(@PathVariable int id) {
        Authorization.verify(request);
        List<Court> courts = TableQueryHelper.query(Court.class, id);
        if (courts.size() == 1) {
            Court court = courts.get(0);
            return court;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/api/court", method = RequestMethod.POST)
    @ResponseBody
    public void createCourt(@RequestBody String body) {
        Authorization.verify(request);
        Court court = ControllerHelper.generateObjectByJsonString(body, Court.class);
        TableInsertHelper.insert(court);
        throw new ResponseStatusException(HttpStatus.OK);
    }

    @RequestMapping(value = "/api/court", method = RequestMethod.PUT)
    @ResponseBody
    public String updateCourt(@RequestBody String body) {
        Authorization.verify(request);
        int lineNumber = TableUpdateHelper.update(body, Court.class);
        if(lineNumber > 0) {
            throw new ResponseStatusException(HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/api/court/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteCourt(@PathVariable int id) {
        Authorization.verify(request);
        int lineNumber = TableDeleteHelper.delete(id, Court.class);
        if(lineNumber > 0) {
            throw new ResponseStatusException(HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
