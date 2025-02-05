package guru.sfg.beer.inventory.service.web.controllers;

import guru.sfg.beer.inventory.service.domain.BeerInventory;
import guru.sfg.beer.inventory.service.repositories.BeerInventoryRepository;
import guru.sfg.beer.inventory.service.web.mappers.BeerInventoryMapper;
import guru.sfg.brawery.model.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by jt on 2019-05-31.
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class BeerInventoryController {

    private final BeerInventoryRepository beerInventoryRepository;
    private final BeerInventoryMapper beerInventoryMapper;

    @GetMapping("api/v1/beer/{beerId}/inventory")
    List<BeerInventoryDto> listBeersById(@PathVariable UUID beerId){
        log.debug("Finding Inventory for beerId:" + beerId);

        return beerInventoryRepository.findAllByBeerId(beerId)
                .stream()
                .map(beerInventoryMapper::beerInventoryToBeerInventoryDto)
                .collect(Collectors.toList());
    }

    @GetMapping("api/v1/beer")
    public List<BeerInventoryDto> getBeers(){
        log.debug("Get all beers.");

        List<BeerInventory> all = beerInventoryRepository.findAll();
        BeerInventory beerInventory = all.get(0);

        Optional<BeerInventory> byId = beerInventoryRepository.findById(beerInventory.getId());

        return all.stream()
                .map(beerInventoryMapper::beerInventoryToBeerInventoryDto)
                .collect(Collectors.toList());
    }

    @GetMapping("api/v1/beer/{id}")
    public BeerInventoryDto getBeers(@PathVariable UUID id){
        log.debug("Get single by id.");

        int size = CacheManager.ALL_CACHE_MANAGERS.get(0)
                .getCache("guru.sfg.beer.inventory.service.domain.BeerInventory").getSize();

        Optional<BeerInventory> byId = beerInventoryRepository.findById(id);

        return null;
    }
}
