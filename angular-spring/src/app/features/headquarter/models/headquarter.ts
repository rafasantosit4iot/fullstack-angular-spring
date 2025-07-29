import { CountrySummaryItem } from "../../country/models/country";
import { PublisherSummaryItem } from "../../publisher/models/publisher";

export interface HeadquarterSummaryItem {
    id: string;
    street: string;
    city: string;
    countryName: string;
}

interface HeadquarterResponseItem {
    id: string;
    street: string;
    city: string;
    state: string;
    number: number;
    country: CountrySummaryItem;
    publisher: PublisherSummaryItem;
}