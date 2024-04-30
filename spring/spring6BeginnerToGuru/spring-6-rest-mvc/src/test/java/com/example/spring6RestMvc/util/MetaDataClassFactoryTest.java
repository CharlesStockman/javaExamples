package com.example.spring6RestMvc.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MetaDataClassFactoryTest {

    @Test
    public void createMetaClassInstance() {
        MetaDataFactory metadataFactory = new MetaDataFactory();
        Assertions.assertNotNull(metadataFactory);
        Assertions.assertEquals(metadataFactory.getClass(), MetaDataFactory.class);
    }

    @Test
    public void createMetaDataCall() {
        MetaDataFactory metaDataFactory = new MetaDataFactory();
        MetaDataFactory.MetaData metaData = MetaDataFactory.createMetaData();
        Assertions.assertNotNull(metaData);
        Assertions.assertEquals(MetaDataFactory.MetaData.class, metaData.getClass());
    }

    @Test
    public void updateMetaDataCall() {
        MetaDataFactory metaDataFactory = new MetaDataFactory();
        MetaDataFactory.MetaData metaData = MetaDataFactory.updateMetaData(new MetaDataFactory.MetaData());
        Assertions.assertNotNull(metaData);
        Assertions.assertEquals(MetaDataFactory.MetaData.class, metaData.getClass() );
    }

    @Test
    public void updateMetaDataCallWithNullMetaData() {
        MetaDataFactory metaDataFactory = new MetaDataFactory();
        InvalidParameterException exception =
                assertThrows(InvalidParameterException.class, () -> MetaDataFactory.updateMetaData(null));
    }

    @Test
    public void validateCreateMetaData() {
        MetaDataFactory metaDataFactory = new MetaDataFactory();
        MetaDataFactory.MetaData metaData = MetaDataFactory.createMetaData();
        verifyCommonMetaData(metaData);
        Assertions.assertEquals(metaData.getVersion(), 1);
    }

    @Test
    public void validateModifyMetaData() {
        MetaDataFactory metaDataFactory = new MetaDataFactory();
        MetaDataFactory.MetaData metaData = MetaDataFactory.modifyMetaData(MetaDataFactory.createMetaData());
        verifyCommonMetaData(metaData);
        Assertions.assertEquals(metaData.getVersion(), 2);
    }

    private static void verifyCommonMetaData(MetaDataFactory.MetaData metaData) {
        Assertions.assertNotNull(metaData);
        Assertions.assertNotNull(metaData.getId());
        Assertions.assertNotNull(metaData.getCreatedDate());
        Assertions.assertNotNull(metaData.getModifiedDate());

        Assertions.assertTrue(metaData.getCreatedDate().isBefore(metaData.getModifiedDate()));
        Assertions.assertTrue(metaData.getCreatedDate().isAfter(LocalDateTime.now().minusSeconds(1000)));
        Assertions.assertTrue(metaData.getModifiedDate().isAfter(LocalDateTime.now().minusSeconds(1000)));
    }


}
