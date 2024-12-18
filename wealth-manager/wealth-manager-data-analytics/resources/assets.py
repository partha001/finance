import io
from base64 import encodebytes

import PIL.Image
from flask.views import MethodView
from flask_smorest import Blueprint, abort

from services.AssetService import getAssetChartAssetsVsTime

blp = Blueprint("assets",__name__,description="operations on assets")

@blp.route("/assets")
class Assets(MethodView):

    def get(self):
        test = getAssetChartAssetsVsTime()
        return test,200

@blp.route("/assets/chart/assetsVsTime")
class AssetChartOperations(MethodView):

    def get(self):
        test = getAssetChartAssetsVsTime()
        return test,200





