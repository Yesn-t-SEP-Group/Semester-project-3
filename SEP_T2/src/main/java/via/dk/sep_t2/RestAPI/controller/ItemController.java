package via.dk.sep_t2.RestAPI.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import via.dk.sep_t2.RestAPI.model.Item;
import via.dk.sep_t2.RestAPI.model.SearchItemParameters;
import via.dk.sep_t2.RestAPI.repository.ItemRepository;

import java.time.LocalDate;
import java.util.ArrayList;

@RestController
@RequestMapping("/")
public class ItemController
{
    private final ItemRepository repository;

    ItemController(ItemRepository repository)
    {
        this.repository = repository;
    }

    @RequestMapping(value = "/items/{id}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Item getItemById(@PathVariable int id)
    {
        return repository.read(id);
    }

    @RequestMapping(value = "/items",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Item newItem(@RequestBody Item item)
    {
        return repository.create(item);
    }

    @RequestMapping(value = "/items",
            method = RequestMethod.GET//,
            //produces = {MediaType.APPLICATION_XML_VALUE}
    )
    public ArrayList<Item> all(@RequestParam(required = false) String condition,
                               @RequestParam(required = false) String type)
    {
        SearchItemParameters searchItemParameters = new SearchItemParameters(condition,type);
        return repository.findWithFilters(searchItemParameters);
    }


    @RequestMapping(value = "/items/{id}",
            method = RequestMethod.PUT,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Item replace(@RequestBody Item newItem, int id)
    {
        return repository.update(id, newItem);
    }

    @RequestMapping(value = "/items/{id}",
            method = RequestMethod.DELETE,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public void delete(@PathVariable int id)
    {
        repository.delete(id);
    }
}
