package ifgram.dto;

import jakarta.validation.constraints.NotBlank;

public record PostRequest(
        @NotBlank
        String imagemUrl,

        @NotBlank
        String legenda,

        @NotBlank
        String autor

        ) {


}