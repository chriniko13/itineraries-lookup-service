package com.adidas.chriniko.itinerarieslookupservice.mapper;

import java.util.List;

public interface Mapper<Source, Destination> {

    List<Destination> transform(List<Source> input);

    Destination transform(Source input);

}
