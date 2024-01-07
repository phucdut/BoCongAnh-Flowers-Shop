package com.example.admin.Domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ImportGoodsDto {

    List<ImportGoodsDetailDto> importGoodDetails = new ArrayList<>();
}
