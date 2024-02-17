package com.abx.ainotebook.configuration;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.UUID;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

@Configuration
public class MongoConfig {

    @Bean
    public MongoCustomConversions customConversions() {
        return new MongoCustomConversions(Arrays.asList(new UuidToBinaryConverter(), new BinaryToUuidConverter()));
    }

    @WritingConverter
    public static class UuidToBinaryConverter implements Converter<UUID, Binary> {
        @Override
        public Binary convert(UUID source) {
            ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
            bb.putLong(source.getMostSignificantBits());
            bb.putLong(source.getLeastSignificantBits());
            Binary binary = new Binary(BsonBinarySubType.UUID_STANDARD, bb.array());
            return binary;
        }
    }

    @ReadingConverter
    public static class BinaryToUuidConverter implements Converter<Binary, UUID> {
        @Override
        public UUID convert(Binary source) {
            ByteBuffer bb = ByteBuffer.wrap(source.getData());
            long firstLong = bb.getLong();
            long secondLong = bb.getLong();
            UUID uuid = new UUID(firstLong, secondLong);
            return uuid;
        }
    }
}
