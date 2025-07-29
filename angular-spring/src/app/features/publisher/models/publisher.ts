import { ApiResponseBase } from "../../../shared/models/response";
import { HeadquarterSummaryItem } from "../../headquarter/models/headquarter";

export interface PublisherSummaryItem {
    id: string;
    name: string;
    founder: string;
    foundationYear: number;
}

export interface PublisherResponseItem {
    id: string;
    name: string;
    founder: string;
    foundationYear: number;
    headquarters: HeadquarterSummaryItem[];
}

export interface PublisherResponse extends ApiResponseBase {
    content: PublisherResponseItem[];
}

export interface PublisherCreateBody {
    name: string;
    founder: string;
    foundationYear: number;
}