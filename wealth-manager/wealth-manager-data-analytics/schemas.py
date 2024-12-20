from marshmallow import Schema, fields


class InstrumentDataDownloadRequestSchema(Schema):
    ticker = fields.Str(required=True)
    key = fields.Str(required=True)
    start_date = fields.Str(required=True)
    end_date = fields.Str(required=True)
