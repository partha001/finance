import uuid
from flask import request
from flask.views import MethodView
from flask_smorest import Blueprint, abort
from services.AssetService import printHello


blp = Blueprint("assets",__name__,description="operations on assets")

@blp.route("/assets")
class Assets(MethodView):

    def get(self):
        test = printHello()
        return {"assets": test},200


