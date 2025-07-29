import { ApiResponseBase } from "../../../shared/models/response";

export interface CountrySummaryItem {
    id: string;
    name: string;
}

export interface CountryResponseItem {
    id: string;
    name: string;
    isoCode:string ;
}

export interface CountryResponse extends ApiResponseBase {
    content: CountryResponseItem[];
}

export interface CountryCreateBody {
    name: string;
    isoCode: string
}