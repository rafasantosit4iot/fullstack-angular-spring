import { ApiResponseBase } from "../../../shared/models/response";
import { CountrySummaryItem } from "../../country/models/country";
import { PublisherSummaryItem } from "../../publisher/models/publisher";

export interface HeadquarterSummaryItem {
    id: string;
    street: string;
    city: string;
    countryName: string;
}

export interface HeadquarterResponseItem {
    id: string;
    street: string;
    city: string;
    state: string;
    number: number;
    country: CountrySummaryItem;
    publisher: PublisherSummaryItem;
}

export interface HeadquarterPageResponse extends ApiResponseBase{
    content: HeadquarterResponseItem[];
}

export interface HeadquarterCreateBody {
    city: string;
    state: string;
    street: string;
    number: number;
    zipCode: string;
    countryId: number;
    publisherId: string;
}