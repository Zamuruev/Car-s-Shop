package CarShop.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferDTO {
    public String id;
    public String user_id;
    public String image_url = "/img/offer.png";
}
