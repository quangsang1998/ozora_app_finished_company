package com.duonghb.testbitrise.domain.usecase

import com.duonghb.testbitrise.data.repository.ContentRepository
import com.duonghb.testbitrise.domain.model.TabContent
import com.duonghb.testbitrise.domain.model.ListItemSubCategory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetListSubContentUseCase @Inject constructor(
    private val contentRepository: ContentRepository
) {
    suspend operator fun invoke(
        client_id: Int,
        category_id: Int,
        subCategory: ListItemSubCategory? = null,
        limit: Int,
        offset: Int
    ): Flow<TabContent> {
        return contentRepository.getSubContents(
            client_id, category_id, subCategory, limit, offset
        )
    }
}
