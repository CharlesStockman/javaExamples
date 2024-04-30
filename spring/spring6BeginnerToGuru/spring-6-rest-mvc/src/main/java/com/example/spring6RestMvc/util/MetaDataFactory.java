package com.example.spring6RestMvc.util;

import lombok.Data;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.UUID;

public class MetaDataFactory {

    @Data
    public static class MetaData {
        private UUID id;
        private Integer version;
        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate;

    }

    public static MetaData createMetaData() {
        MetaData metaData = new MetaData();
        metaData.setId(UUID.randomUUID());
        metaData.setCreatedDate(LocalDateTime.now());
        metaData.setModifiedDate(LocalDateTime.now());
        metaData.setVersion(0);
        return metaData;
    }

    public static MetaData modifyMetaData(MetaData metaData) {
        metaData.setModifiedDate(LocalDateTime.now());
        metaData.setVersion( metaData.getVersion() + 1);
        return metaData;
    }

    public static MetaData updateMetaData(MetaData metaData) {

        if ( metaData == null )
            throw new InvalidParameterException("MetaData Parameter cannot be null since we are updating the metadata");
        return metaData;
    }
}
