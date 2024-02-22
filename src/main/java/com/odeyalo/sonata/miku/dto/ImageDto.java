package com.odeyalo.sonata.miku.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImageDto {
    @NotNull
    String url;
    @Nullable
    Integer width;
    @Nullable
    Integer height;
}
