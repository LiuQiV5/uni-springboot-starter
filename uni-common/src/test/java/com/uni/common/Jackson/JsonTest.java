package com.uni.common.Jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.uni.common.config.UniCustomNullJsonSerializer;
import com.uni.common.config.UniCustomSerializerModifier;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.setSerializerFactory(OBJECT_MAPPER.getSerializerFactory().withSerializerModifier(new UniCustomSerializerModifier()));
        SerializerProvider serializerProvider = OBJECT_MAPPER.getSerializerProvider();
        serializerProvider.setNullValueSerializer(new UniCustomNullJsonSerializer.NullObjectJsonSerializer());
    }

    @Getter
    @Setter
    @Builder
    private static class User {
        private Long age;
        private String name;
        private List<String> hobby;
        private Boolean marriage;
        private Address address;
    }

    @Getter
    @Setter
    @Builder
    private static class Address {
        private String detail;
    }


    @Test
    public void nullStringJsonSerializerTest() throws JsonProcessingException {
        User user = User
                .builder()
                .name(null)
                .age(26L)
                .marriage(true)
                .hobby(Collections.singletonList("足球"))
                .address(Address.builder().detail("江阴").build())
                .build();
        String value = OBJECT_MAPPER.writeValueAsString(user);
        assertThat(value).contains("");
        assertThat(value).doesNotContain("null");
    }

    @Test
    public void NullNumberJsonSerializer() throws JsonProcessingException {
        User user = User
                .builder()
                .name("小明")
                .age(null)
                .marriage(true)
                .hobby(Collections.singletonList("足球"))
                .address(Address.builder().detail("江阴").build())
                .build();
        String value = OBJECT_MAPPER.writeValueAsString(user);
        assertThat(value).contains("0");
        assertThat(value).doesNotContain("null");
    }

    @Test
    public void NullBooleanJsonSerializer() throws JsonProcessingException {
        User user = User
                .builder()
                .name("小明")
                .age(26L)
                .marriage(null)
                .hobby(Collections.singletonList("足球"))
                .address(Address.builder().detail("江阴").build())
                .build();
        String value = OBJECT_MAPPER.writeValueAsString(user);
        assertThat(value).contains("false");
        assertThat(value).doesNotContain("null");
    }

    @Test
    public void NullObjectJsonSerializer() throws JsonProcessingException {
        User user = User
                .builder()
                .name("小明")
                .age(26L)
                .marriage(true)
                .hobby(Collections.singletonList("足球"))
                .address(null)
                .build();
        String value = OBJECT_MAPPER.writeValueAsString(user);
        assertThat(value).contains("{}");
        assertThat(value).doesNotContain("null");
    }

    @Test
    public void NullArrayJsonSerializer() throws JsonProcessingException {
        User user = User
                .builder()
                .name("小明")
                .age(26L)
                .marriage(true)
                .hobby(null)
                .address(Address.builder().detail("江阴").build())
                .build();
        String value = OBJECT_MAPPER.writeValueAsString(user);
        assertThat(value).contains("[]");
        assertThat(value).doesNotContain("null");
    }
}
