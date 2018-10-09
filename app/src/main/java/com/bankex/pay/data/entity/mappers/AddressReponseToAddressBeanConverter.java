package com.bankex.pay.data.entity.mappers;


import com.bankex.pay.data.entity.mappers.value.ValueConverter;
import com.bankex.pay.domain.BaseDomainBean;
import com.bankex.pay.domain.ErrorMessage;
import com.bankex.pay.domain.models.BaseBankexModel;
import com.bankex.pay.domain.models.address.AddressModel;
import com.bankex.pay.domain.models.network.AddressResponse;
import com.bankex.pay.domain.models.network.BaseHeadResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Converter;

/**
 * @author Gevork Safaryan on 18.06.2018
 */
public class AddressReponseToAddressBeanConverter implements Converter<BaseHeadResponse<AddressResponse>, BaseDomainBean<List<BaseBankexModel>>> {

    @Override
    public BaseDomainBean<List<BaseBankexModel>> convert(BaseHeadResponse<AddressResponse> value) throws IOException {
        BaseDomainBean<List<BaseBankexModel>> baseDomainBean = new BaseDomainBean();
        List<BaseBankexModel> baseAddressModels = new ArrayList<>();

        if (value.getHead() != null) {
            AddressResponse valueHead = value.getHead();

            AddressModel addressModel = new AddressModel();
            addressModel.setAddr(valueHead.getAddr());
            addressModel.setBalance(new ValueConverter()
                    .convertBalanceToNormalWithDecimals(
                            valueHead.getBalance(),
                            valueHead.getDecimals()));
            addressModel.setAddr(valueHead.getAddr());
            addressModel.setTotaltokens(valueHead.getTotaltokens());
            baseAddressModels.add(addressModel);

            baseDomainBean.setSuccessObject(baseAddressModels);
        }

        if (value.getError() != null && value.getError().length() > 0) {
            baseDomainBean.setErrorObject(createErrorMessage(value.getError()));
        }
        return baseDomainBean;
    }

    private ErrorMessage createErrorMessage(String error) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrors(error);
        return errorMessage;
    }
}
