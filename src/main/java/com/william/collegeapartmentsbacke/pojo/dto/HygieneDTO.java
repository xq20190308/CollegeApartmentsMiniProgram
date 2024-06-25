package com.william.collegeapartmentsbacke.pojo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
public class HygieneDTO {
        @JsonProperty("1")

        private String Dormitoryid;
        @JsonProperty("2")

        private String FirstRank;
        @JsonProperty("3")

        private String SecondRank;
        @JsonProperty("4")

        private String ThirdRank;
        @JsonProperty("5")

        private String FourthRank;
        @JsonProperty("6")

        private String FifthRank;
        @JsonProperty("7")

        private String SixthRank;
        @JsonProperty("8")

        private String SeventhRank;
        @JsonProperty("9")

        private String EighthRank;
        @JsonProperty("10")

        private String NinthRank;
        @JsonProperty("11")

        private String TenthRank;
        @JsonProperty("12")

        private String EleventhRank;
        @JsonProperty("13")

        private String TwelfthRank;
        @JsonProperty("14")

        private String ThirteenthRank;
        @JsonProperty("15")

        private String FourteenthRank;
        @JsonProperty("16")

        private String FifteenthRank;
        @JsonProperty("17")

        private String SixteenthRank;
        @JsonProperty("18")

        private String SeventeenthRank;
        @JsonProperty("19")

        private String EighteenthRank;
        @JsonProperty("20")

        private String NineteenthRank;
}
