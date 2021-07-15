package com.duonghb.testbitrise.domain.usecase

import com.duonghb.testbitrise.data.repository.ContentRepository
import com.duonghb.testbitrise.domain.model.ListItemSubCategory
import com.duonghb.testbitrise.domain.model.SubCategoryModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetListSubCategoriesUseCase @Inject constructor(
    private val contentRepository: ContentRepository
) {
    suspend operator fun invoke(
        client_id: Int,
        id: Int,
    ): Flow<List<ListItemSubCategory>> {
        return contentRepository.getSubCategories(client_id, id)
    }
}
