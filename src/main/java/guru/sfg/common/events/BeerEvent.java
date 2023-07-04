package guru.sfg.common.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by radek on 2023-07-04
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerEvent implements Serializable {

    static final long serialVersionUID = -5215973007899621882L;

    private BeerDto beerDto;
}
