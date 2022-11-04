package com.saber.spring_boot_web_demo.services.routes.persons;

import com.saber.spring_boot_web_demo.dto.persons.PersonDto;
import com.saber.spring_boot_web_demo.services.routes.AbstractRestRouteBuilder;
import com.saber.spring_boot_web_demo.services.routes.Routes;
import org.apache.camel.Exchange;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class PersonFindByNationalCodeRoute extends AbstractRestRouteBuilder {
    
    @Override
    public void configure() throws Exception {
        super.configure();

        from(String.format("direct:%s", Routes.FIND_PERSON_BY_NationalCode_ROUTE))
                .routeId(Routes.FIND_PERSON_BY_NationalCode_ROUTE)
                .routeGroup(Routes.FIND_PERSON_BY_NationalCode_ROUTE_GROUP)
                .log("Request for correlation : ${in.header.correlation} find  person with nationalCode ${in.header.nationalCode}")
                .to(String.format("direct:%s", Routes.FIND_PERSON_BY_NationalCode_ROUTE_GATEWAY))
                .to(String.format("direct:%s", Routes.FIND_PERSON_BY_NationalCode_ROUTE_GATEWAY_OUT));

        from(String.format("direct:%s", Routes.FIND_PERSON_BY_NationalCode_ROUTE_GATEWAY))
                .routeId(Routes.FIND_PERSON_BY_NationalCode_ROUTE_GATEWAY)
                .routeGroup(Routes.FIND_PERSON_BY_NationalCode_ROUTE_GROUP)
                .log("Request for correlation : ${in.header.correlation} find  person with nationalCode ${in.header.nationalCode}")
                .toD("sql:select * from persons where nationalCode=${in.header.nationalCode}?outputType=selectOne&outputClass="+ PersonDto.class.getName())
                .log("Response for correlation : ${in.header.correlation} find  person with nationalCode ${in.header.nationalCode} ==> ${in.body} ")
                .setHeader(Exchange.HTTP_RESPONSE_CODE,constant(200)) ;

        from(String.format("direct:%s", Routes.FIND_PERSON_BY_NationalCode_ROUTE_GATEWAY_OUT))
                .routeId(Routes.FIND_PERSON_BY_NationalCode_ROUTE_GATEWAY_OUT)
                .routeGroup(Routes.FIND_PERSON_BY_NationalCode_ROUTE_GROUP)
                .choice()
                .when(body().isNull())
                    .to(String.format("direct:%s", Routes.RESOURCE_NOTFOUND_RESPONSE_ROUTE))
                .otherwise()
                .marshal().json(JsonLibrary.Jackson)
                .unmarshal().json(JsonLibrary.Jackson, PersonDto.class)
                .log("Response for correlation : ${in.header.correlation} say Hello ===> ${in.body}")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200))
                .end();
    }

}
