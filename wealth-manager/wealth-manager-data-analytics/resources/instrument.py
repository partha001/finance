from flask import request
from flask.views import MethodView
from flask_smorest import Blueprint

from schemas import InstrumentDataDownloadRequestSchema, InstrumentDataDownloadResponseSchema
from services.InstrumentService import downloadInstrumentDailyData, generateTechnicalChart

blp = Blueprint("instrument", __name__, description="operations on instruments")


@blp.route("/instruments/downloadDailyData")
class Instruments(MethodView):
    def get(self):
        return generateTechnicalChart()

    @blp.arguments(InstrumentDataDownloadRequestSchema)
    @blp.response(200, InstrumentDataDownloadResponseSchema)
    def post(self, payload):
        print(payload)
        key = payload.get('key')
        ticker = payload.get('ticker')
        startDate = payload.get('startDate')
        endDate = payload.get('endDate')
        return downloadInstrumentDailyData(ticker, key, startDate, endDate)
