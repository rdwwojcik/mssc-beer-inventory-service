package guru.sfg.common.events;

import lombok.NoArgsConstructor;

/**
 * Created by radek on 2023-07-04
 */
@NoArgsConstructor
public class NewInventoryEvent extends BeerEvent{

    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
