package com.contents.domain.interactor

import com.contents.domain.model.UserLocation

fun interface SaveUserLocationUseCase: suspend (UserLocation) -> Long