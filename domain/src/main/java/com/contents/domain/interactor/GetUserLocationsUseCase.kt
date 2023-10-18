package com.contents.domain.interactor

import com.contents.domain.model.UserLocation
import kotlinx.coroutines.flow.Flow


fun interface GetUserLocationsUseCase: suspend () -> Flow<List<UserLocation>>