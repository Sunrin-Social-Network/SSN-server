package io.twotle.ssn.dto.meal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Row{
    @JsonProperty("ATPT_OFCDC_SC_CODE")
    public String aTPT_OFCDC_SC_CODE;
    @JsonProperty("ATPT_OFCDC_SC_NM")
    public String aTPT_OFCDC_SC_NM;
    @JsonProperty("SD_SCHUL_CODE")
    public String sD_SCHUL_CODE;
    @JsonProperty("SCHUL_NM")
    public String sCHUL_NM;
    @JsonProperty("MMEAL_SC_CODE")
    public String mMEAL_SC_CODE;
    @JsonProperty("MMEAL_SC_NM")
    public String mMEAL_SC_NM;
    @JsonProperty("MLSV_YMD")
    public String mLSV_YMD;
    @JsonProperty("MLSV_FGR")
    public String mLSV_FGR;
    @JsonProperty("DDISH_NM")
    public String dDISH_NM;
    @JsonProperty("ORPLC_INFO")
    public String oRPLC_INFO;
    @JsonProperty("CAL_INFO")
    public String cAL_INFO;
    @JsonProperty("NTR_INFO")
    public String nTR_INFO;
    @JsonProperty("MLSV_FROM_YMD")
    public String mLSV_FROM_YMD;
    @JsonProperty("MLSV_TO_YMD")
    public String mLSV_TO_YMD;
}
