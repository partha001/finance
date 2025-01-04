from marshmallow import Schema, fields


class InstrumentDataDownloadRequestSchema(Schema):
    ticker = fields.Str(required=True)
    key = fields.Str(required=True)
    startDate = fields.Str(required=True)
    endDate = fields.Str(required=True)

class InstrumentDataDownloadResponseSchema(Schema):
    ticker = fields.Str()
    key = fields.Str()
    start_date = fields.Str()
    end_date = fields.Str()
    recordsFetched = fields.Number()
    recordsInserted = fields.Number()

