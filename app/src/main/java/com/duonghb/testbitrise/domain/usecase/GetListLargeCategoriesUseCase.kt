package com.duonghb.testbitrise.domain.usecase

import com.duonghb.testbitrise.data.repository.ContentRepository
import com.duonghb.testbitrise.domain.model.LargeCategoryModel
import com.duonghb.testbitrise.domain.model.ListItemLargeCategory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetListLargeCategoriesUseCase @Inject constructor(
    private val contentRepository: ContentRepository
) {
    suspend operator fun invoke(client_id: Int): Flow<List<ListItemLargeCategory>> {
        return contentRepository.getLargeCategories(client_id)
    }
}
